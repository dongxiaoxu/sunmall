package site.dongxiaoxu.sunmall.framework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseDao<T> extends JpaRepository<T, String> {
}
