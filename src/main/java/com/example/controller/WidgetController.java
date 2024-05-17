package com.example.controller;

import com.example.model.Widget;
import com.example.service.WidgetService;
import com.example.service.WidgetServiceException;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/api/widgets")
public class WidgetController {

    private final WidgetService widgetService;


    private static final Logger LOG = LoggerFactory.getLogger(WidgetController.class);

    public WidgetController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @ExecuteOn(TaskExecutors.BLOCKING)
    @Produces(MediaType.TEXT_PLAIN)
    @Post
    public String createWidget(@Body Widget widget) throws WidgetServiceException {
        return widgetService.saveWidget(widget);
    }


    @ExecuteOn(TaskExecutors.BLOCKING)
    @Get("/search")
    public List<Widget> searchWidgets(@QueryValue String query) throws WidgetServiceException {

        LOG.info("Searching for '{}'", query);
        return widgetService.search(query);
    }


}
