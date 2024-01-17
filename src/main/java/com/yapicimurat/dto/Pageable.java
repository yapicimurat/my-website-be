package com.yapicimurat.dto;


import java.util.Collection;

public class Pageable<T> {
    private Collection<T> elements;
    private int totalPages;
    private long totalElementsPerPage;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPrevious;

    public Pageable(Collection<T> elements, int totalPages, long totalElementsPerPage, int currentPage, boolean hasNext, boolean hasPrevious) {
        this.elements = elements;
        this.totalPages = totalPages;
        this.totalElementsPerPage = totalElementsPerPage;
        this.currentPage = currentPage;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElementsPerPage() {
        return totalElementsPerPage;
    }

    public void setTotalElementsPerPage(long totalElementsPerPage) {
        this.totalElementsPerPage = totalElementsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Collection<T> getElements() {
        return elements;
    }

    public void setElements(Collection<T> elements) {
        this.elements = elements;
    }
}
