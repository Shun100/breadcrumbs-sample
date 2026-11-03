package com.example.breadcrumb.service;

import com.example.breadcrumb.model.BreadcrumbItem;
import com.example.breadcrumb.model.Menu;
import com.example.breadcrumb.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
    prefix = "app.breadcrumb",
    name = "storage",
    havingValue = "parent",
    matchIfMissing = true
)
public class MenuBreadcrumbService implements BreadcrumbService {
  private final MenuRepository menuRepository;

  /**
   * パンくずリスト取得
   * @param menuId - メニューID
   * @return breadcrumbs - パンくずリスト
   */
  @Override
  @Transactional(readOnly = true)
  public List<BreadcrumbItem> getBreadcrumb(Long menuId) {
    List<BreadcrumbItem> result = new ArrayList<>();
    Set<Long> visited = new HashSet<>(); // 循環参照チェック用

    Menu current = menuRepository.findById(menuId)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Menu not found: " + menuId
        ));

    // 最上位の親に到達するまで親の探索を繰り返す
    while (current != null) {
      // 循環参照チェック
      if (!visited.add(current.getId())) {
        throw new IllegalStateException("Circular menu hierarchy detected");
      }

      result.add(new BreadcrumbItem(current.getId(), current.getName()));

      // 上位の親を検索
      current = menuRepository.findParentByChildId(current.getId())
          .orElse(null);
    }

    Collections.reverse(result);

    return result;
  }
}
