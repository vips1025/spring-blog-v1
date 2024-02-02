package shop.mtcoding.blog.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog._core.Constant;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public int count() {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM board_tb");
        BigInteger count = (BigInteger) query.getSingleResult();
        return count.intValue();
    }

    public List<Board> findAll(int page) {
        int value = page * Constant.PAGING_COUNT;
        Query query = em.createNativeQuery("SELECT * FROM board_tb ORDER BY id DESC limit ?,?", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, Constant.PAGING_COUNT);
        List<Board> boardList = query.getResultList();
        return boardList;
    }
}
