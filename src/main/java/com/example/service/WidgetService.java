package com.example.service;

import com.example.model.Widget;

import java.util.List;

public interface WidgetService {

    String saveWidget(Widget widget) throws WidgetServiceException;

    List<Widget> search(String query) throws WidgetServiceException;

}
