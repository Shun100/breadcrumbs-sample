package com.example.breadcrumb.service;

import com.example.breadcrumb.model.BreadcrumbItem;
import com.example.breadcrumb.repository.LtreeMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
    prefix = "app.breadcrumb",
    name = "storage",
    havingValue = "ltree"
)
public class LtreeMenuBreadcrumbService implements BreadcrumbService {
  private final LtreeMenuRepository ltreeMenuRepository;

  /**
   * パンくずリスト取得
   * @param menuId - メニューID
   * @return breadcrumbItems - パンくずリスト
   */
  @Override
  @Transactional(readOnly = true)
  public List<BreadcrumbItem> getBreadcrumb(Long menuId) {
    String path;

    try {
      path = ltreeMenuRepository.findPathById(menuId);
    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found: " + menuId);
    }

    List<BreadcrumbItem> result = ltreeMenuRepository.findByPath(path);

    if (result.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found: " + menuId);
    }

    return result;
  }
}
