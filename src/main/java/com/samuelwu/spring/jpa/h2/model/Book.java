package com.samuelwu.spring.jpa.h2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  private String description;

  @Column(name = "available")
  private boolean available;

  public Book() {}

  public Book(String title, String description, boolean available) {
    this.title = title;
    this.description = description;
    this.available = available;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean isAvailable) {
    this.available = isAvailable;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", available=" + available + "]";
  }

}
