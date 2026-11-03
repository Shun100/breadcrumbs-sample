package com.example.breadcrumb.repository;

import com.example.breadcrumb.model.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuRepository {
  private final JdbcTemplate jdbcTemplate;

  /**
   * メニュー検索 (ID指定)
   * @param id - ID
   * @return menu - メニュ-
   */
  public Optional<Menu> findById(Long id) {
    String sql = "SELECT id, name, parent_id FROM menus WHERE id = ?;";

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> {
          Menu menu = new Menu();
          menu.setId(rs.getLong("id"));
          menu.setName(rs.getString("name"));
          return menu;
        },
        id
    ).stream().findFirst();
  }

  /**
   * 親検索 (子ID指定)
   * @param childId - 子ID
   * @return parentMenu - 親メニュー
   */
  public Optional<Menu> findParentByChildId(Long childId) {
    String sql = """
      SELECT
        p.id,
        p.name
      FROM menus c
      INNER JOIN menus p
        ON c.parent_id = p.id
      WHERE
        c.id = ?;
    """;

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new Menu(
            rs.getLong("id"),
            rs.getString("name"),
            null
        ),
        childId
    ).stream().findFirst();
  }
}
