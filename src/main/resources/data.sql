INSERT INTO menus (id, name, parent_id)
VALUES
    (1, 'ホーム', NULL),
    (2, '商品管理', 1),
    (3, '商品一覧', 2),
    (4, '商品詳細', 3)
ON CONFLICT (id) DO NOTHING;

INSERT INTO ltree_menus (id, name, path) VALUES
    (1, 'ホーム', 'home'),
    (2,'商品管理', 'home.product'),
    (3, '商品一覧', 'home.product.list'),
    (4, '商品詳細', 'home.product.list.detail');
ON CONFLICT (id) DO NOTHING;
