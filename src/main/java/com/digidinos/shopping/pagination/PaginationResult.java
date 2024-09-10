package com.digidinos.shopping.pagination;

import lombok.Data;
import lombok.Getter;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationResult<E> {
    private int totalRecords;
    private int currentPage;
    private List<E> list;
    private int maxResult;
    private int totalPages;
    private int maxNavigationPage;
    private List<Integer> navigationPages;
    // @page: 1, 2, ..
    public PaginationResult(Query<E> query, int page, int maxResult, int maxNavigationPage) {
        final int pageIndex = page - 1 < 0 ? 0 : page - 1;
        int fromRecordIndex = pageIndex * maxResult;

        // Sử dụng setFirstResult và setMaxResults để thực hiện phân trang
        query.setFirstResult(fromRecordIndex);
        query.setMaxResults(maxResult);

        // Lấy danh sách kết quả
        List<E> results = query.list();

        // Tổng số bản ghi có thể tính bằng cách thực hiện thêm một truy vấn đếm bản ghi
        int totalRecords = results.size();

        this.totalRecords = totalRecords;
        this.currentPage = pageIndex + 1;
        this.list = results;
        this.maxResult = maxResult;

        // Tính tổng số trang
        if (this.totalRecords % this.maxResult == 0) {
            this.totalPages = this.totalRecords / this.maxResult;
        } else {
            this.totalPages = (this.totalRecords / this.maxResult) + 1;
        }

        this.maxNavigationPage = maxNavigationPage;
        if (maxNavigationPage < totalPages) {
            this.maxNavigationPage = maxNavigationPage;
        }

        this.calcNavigationPages();
    }
    private void calcNavigationPages() {

        this.navigationPages = new ArrayList<Integer>();
        int current = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
        int begin = current - this.maxNavigationPage / 2;
        int end = current + this.maxNavigationPage / 2;
        // Trang đầu tiên
        navigationPages.add(1);
        if (begin > 2) {
        // Dùng cho '...'
            navigationPages.add(-1);
        }
        for (int i = begin; i < end; i++) {
            if (i > 1 && i < this.totalPages) {
                navigationPages.add(i);
            }
        }
        if (end < this.totalPages - 2) {
        // Dùng cho '...'
            navigationPages.add(-1);
        }
        // Trang cuối cùng.
        navigationPages.add(this.totalPages);
    }
}
