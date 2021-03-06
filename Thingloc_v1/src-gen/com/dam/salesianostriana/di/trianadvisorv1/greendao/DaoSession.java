package com.dam.salesianostriana.di.thinglocv1.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.dam.salesianostriana.di.thinglocv1.greendao.Valoraciones;
import com.dam.salesianostriana.di.thinglocv1.greendao.Sitios;
import com.dam.salesianostriana.di.thinglocv1.greendao.Usuarios;
import com.dam.salesianostriana.di.thinglocv1.greendao.Comentarios;

import com.dam.salesianostriana.di.thinglocv1.greendao.ValoracionesDao;
import com.dam.salesianostriana.di.thinglocv1.greendao.SitiosDao;
import com.dam.salesianostriana.di.thinglocv1.greendao.UsuariosDao;
import com.dam.salesianostriana.di.thinglocv1.greendao.ComentariosDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig valoracionesDaoConfig;
    private final DaoConfig sitiosDaoConfig;
    private final DaoConfig usuariosDaoConfig;
    private final DaoConfig comentariosDaoConfig;

    private final ValoracionesDao valoracionesDao;
    private final SitiosDao sitiosDao;
    private final UsuariosDao usuariosDao;
    private final ComentariosDao comentariosDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        valoracionesDaoConfig = daoConfigMap.get(ValoracionesDao.class).clone();
        valoracionesDaoConfig.initIdentityScope(type);

        sitiosDaoConfig = daoConfigMap.get(SitiosDao.class).clone();
        sitiosDaoConfig.initIdentityScope(type);

        usuariosDaoConfig = daoConfigMap.get(UsuariosDao.class).clone();
        usuariosDaoConfig.initIdentityScope(type);

        comentariosDaoConfig = daoConfigMap.get(ComentariosDao.class).clone();
        comentariosDaoConfig.initIdentityScope(type);

        valoracionesDao = new ValoracionesDao(valoracionesDaoConfig, this);
        sitiosDao = new SitiosDao(sitiosDaoConfig, this);
        usuariosDao = new UsuariosDao(usuariosDaoConfig, this);
        comentariosDao = new ComentariosDao(comentariosDaoConfig, this);

        registerDao(Valoraciones.class, valoracionesDao);
        registerDao(Sitios.class, sitiosDao);
        registerDao(Usuarios.class, usuariosDao);
        registerDao(Comentarios.class, comentariosDao);
    }
    
    public void clear() {
        valoracionesDaoConfig.getIdentityScope().clear();
        sitiosDaoConfig.getIdentityScope().clear();
        usuariosDaoConfig.getIdentityScope().clear();
        comentariosDaoConfig.getIdentityScope().clear();
    }

    public ValoracionesDao getValoracionesDao() {
        return valoracionesDao;
    }

    public SitiosDao getSitiosDao() {
        return sitiosDao;
    }

    public UsuariosDao getUsuariosDao() {
        return usuariosDao;
    }

    public ComentariosDao getComentariosDao() {
        return comentariosDao;
    }

}
