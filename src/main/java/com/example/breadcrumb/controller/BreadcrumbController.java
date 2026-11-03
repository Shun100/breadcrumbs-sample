package com.example.breadcrumb.controller;

import com.example.breadcrumb.model.BreadcrumbItem;
import com.example.breadcrumb.service.BreadcrumbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/breadcrumbs")
@RequiredArgsConstructor
public class BreadcrumbController {
  private final BreadcrumbService breadcrumbService;

  /**
   * パンくずリスト取得
   * @param menuId - メニューID
   * @return response - パンくずリスト情報
   */
  @GetMapping("/{menuId}")
  public List<BreadcrumbItem> getBreadcrumbs(@PathVariable Long menuId) {
    return breadcrumbService.getBreadcrumb(menuId);
  }
}
