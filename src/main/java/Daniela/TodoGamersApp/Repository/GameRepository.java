package Daniela.TodoGamersApp.Repository;

import Daniela.TodoGamersApp.Model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer>{

    Page<Game> findAll(Pageable pageable);

    Game getOne(Integer id);
}
