package com.example.breadcrumb.repository;

import com.example.breadcrumb.model.BreadcrumbItem;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LtreeMenuRepository {
  private final JdbcTemplate jdbcTemplate;

  /**
   * パンくずリスト検索 (パス指定)
   * @param path - パス
   * @return breadcrumbItems - パンくずリスト
   */
  public List<BreadcrumbItem> findByPath(String path) {
    /*
     * ltreeの検索クエリ
     * - path @> CAST(? AS ltree): 指定した階層パスの祖先に該当するレコードを検索
     * - ORDER BY nlevel(path): 階層の浅い順に並べる
     */
    String sql = """
      SELECT id, name
      FROM ltree_menus
      WHERE path @> CAST(? AS ltree)
      ORDER BY nlevel(path)
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) ->
        new BreadcrumbItem(
            rs.getLong("id"),
            rs.getString("name")
        ),
        path
    );
  }

  /**
   * パス検索 (ID指定)
   * @param id - ID
   * @return path - パス
   */
  public String findPathById(Long id) {
    String sql = "SELECT path::text FROM ltree_menus WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, String.class, id);
  }
}
