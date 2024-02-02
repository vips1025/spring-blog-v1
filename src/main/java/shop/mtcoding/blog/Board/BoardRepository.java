package shop.mtcoding.blog.Board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog._core.Constant;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public int count() {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM board_tb");
        Long count = (Long) query.getSingleResult();
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

    public BoardResponse.DetailDTO findById(int id) {
        // Entity가 아닌 것은 JPA가 파싱안해준다.
        Query query = em.createNativeQuery("SELECT bt.id, bt.title, bt.content, bt.created_at, bt.user_id, ut.username FROM board_tb bt INNER JOIN user_tb ut ON bt.user_id = ut.id WHERE bt.id = ?");
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.DetailDTO responseDTO = rm.uniqueResult(query, BoardResponse.DetailDTO.class);
        return responseDTO;
    }
}
