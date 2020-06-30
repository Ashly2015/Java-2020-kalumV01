package edu.kalum.oauth.core.models.service;

import edu.kalum.oauth.core.models.dao.IUsuarioDao;
import edu.kalum.oauth.core.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {
    private Logger logger= LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private IUsuarioDao usuarioDao;
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=usuarioDao.findByUsername(username);
        if(usuario==null){
            logger.error("Error login:No existe usuario con el username [".concat(username).concat("]"));
            throw new UsernameNotFoundException("Error login:No existe usuario con el username [".concat(username).concat("]"));
        }
        List<GrantedAuthority> authorities=usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority->logger.info("Role: ".concat(authority.getAuthority()))).collect(Collectors.toList());
        return new User(usuario.getUsername(),usuario.getPassword(),usuario.isEnabled()
                ,true,true,true,authorities);
    }
}
