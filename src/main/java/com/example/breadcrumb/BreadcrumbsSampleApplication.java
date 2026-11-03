package com.example.breadcrumb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Bootアプリケーションを起動するためのメインクラス（起動クラス）
 * <p>
 *    @SpringBootApplicationは以下の3つを組み合わせたアノテーション
 *    - @SpringBootConfiguration: Springの設定クラスであることを表す。
 *    - @EnableAutoConfiguration: 必要な設定を自動的に行う。
 *    - @ComponentScan: ControllerやServiceなどを自動検出する。
 * </p>
 */
@SpringBootApplication
public class BreadcrumbsSampleApplication {

  /**
   * Spring Bootアプリケーションを起動する。
   * <p>
   *   1. SpringのApplicationContext（DIコンテナ）を生成する。
   *   2. Bean（Controller, Serviceなど）を登録する。
   *   3. 必要な設定を自動的に適用する。
   *   4. Webアプリケーションの場合、組み込みTomcatなどのWebサーバを起動する。
   * </p>
   * @param args - コマンドライン引数
   */
  public static void main(String[] args) {
    SpringApplication.run(BreadcrumbsSampleApplication.class, args);
  }
}
