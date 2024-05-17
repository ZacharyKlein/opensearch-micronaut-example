package com.example.service;

import com.example.model.Widget;
import jakarta.inject.Singleton;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.opensearch.client.opensearch.core.IndexResponse;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Singleton
public class WidgetServiceImpl implements WidgetService {

    private final OpenSearchClient client;

    private static final String INDEX_NAME = "widget";


    private static final Logger LOG = LoggerFactory.getLogger(WidgetServiceImpl.class);

    public WidgetServiceImpl(OpenSearchClient client) {
        this.client = client;
    }

    @Override
    public String saveWidget(Widget widget) throws WidgetServiceException {
        try {
            IndexRequest<Widget> indexRequest = createIndexRequest(widget);
            IndexResponse indexResponse = client.index(indexRequest);
            String id = indexResponse.id();
            LOG.info("Document for '{}' {} successfully in OS. The id is: {}", widget, indexResponse.result(), id);
            return id;
        } catch (Exception e) {
            String errorMessage = String.format("An exception occurred while indexing '%s'", widget);
            LOG.error(errorMessage);
            throw new WidgetServiceException(errorMessage, e);
        }
    }

    private IndexRequest<Widget> createIndexRequest(Widget widget) {
        return new IndexRequest.Builder<Widget>()
            .index(INDEX_NAME)
            .document(widget)
            .build();
    }

    @Override
    public List<Widget> search(String query) throws WidgetServiceException {

        LOG.info("Searching for '{}'", query);

        try {
            List<Widget> results = new ArrayList<>();

            LOG.info("info: {}", client.info().toString());

            SearchResponse<Widget> searchResponse = client.search(s ->
                s.index(INDEX_NAME).query(q ->
                    q.match(m ->
                        m.field("name").query(fq -> fq.stringValue(query)))), Widget.class);
            LOG.info("Searching for '{}' took {} and found {}",
                query,
                searchResponse.took(),
                searchResponse.hits().total().value());

            //convert iterator to list of type Widget
            for (Hit<Widget> hit : searchResponse.hits().hits()) {
                Widget widget = hit.source();
                LOG.info("Found widget: {}", widget);
                results.add(widget);
            }

            return results;
        } catch (Exception e) {
            String errorMessage = String.format("An exception occurred while searching for query '%s'", query);
            LOG.error(errorMessage);
            throw new WidgetServiceException(errorMessage, e);
        }
    }
}
