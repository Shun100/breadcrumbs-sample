package com.example.breadcrumb.service;

import com.example.breadcrumb.model.BreadcrumbItem;

import java.util.List;

public interface BreadcrumbService {
  /**
   * パンくずリスト取得
   * @param menuId - メニューID
   * @return breadcrumbItems - パンくずリスト
   */
  List<BreadcrumbItem> getBreadcrumb(Long menuId);
}
