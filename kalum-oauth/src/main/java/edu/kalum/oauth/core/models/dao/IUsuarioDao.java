package edu.kalum.oauth.core.models.dao;

import edu.kalum.oauth.core.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDao extends JpaRepository<Usuario,Long> {
    public Usuario findByUsername(String username);

    //@Query("select u from Usuario u where u.username=?1")
    //public Usuario getUsuario(String username);

}
