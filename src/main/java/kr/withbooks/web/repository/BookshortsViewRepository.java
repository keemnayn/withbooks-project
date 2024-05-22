package kr.withbooks.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.withbooks.web.entity.BookshortsView;

@Mapper
public interface BookshortsViewRepository {
    
    List<BookshortsView> findAll(Long bookId, Long userId, int size, int offset);

    // main
    List<BookshortsView> findAllBestShorts();

    // /api/bookShorts/list
    List<BookshortsView> findAllViews(Long bookId, Long userId, Long lastShortsId);

    // admin/user
    List<BookshortsView> findById(Long id);
}
