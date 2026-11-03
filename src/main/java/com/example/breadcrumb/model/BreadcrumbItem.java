package com.example.breadcrumb.model;

// parent_id型とltree型で共用のDTO
public record BreadcrumbItem(
    long id,
    String name
) {}
