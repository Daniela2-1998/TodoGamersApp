package Daniela.TodoGamersApp.Repository;

import Daniela.TodoGamersApp.Model.Platform;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlatformRepository extends CrudRepository<Platform, Integer>{

    List<Platform> findAll(Sort titulo);
}
