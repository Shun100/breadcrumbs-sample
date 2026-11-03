package com.example.breadcrumb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// parent_id型のMenuのEntity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu {
  private Long id;
  private String name;
  private Menu parent;
}
