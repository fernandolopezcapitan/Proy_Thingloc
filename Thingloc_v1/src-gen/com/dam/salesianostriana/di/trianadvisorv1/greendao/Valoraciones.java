package com.dam.salesianostriana.di.thinglocv1.greendao;

import com.dam.salesianostriana.di.thinglocv1.greendao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "VALORACIONES".
 */
public class Valoraciones {

    private Long id;
    private String objectId;
    private String updateAt;
    private String valoracion;
    private long idUsuarioV;
    private long idSitioV;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ValoracionesDao myDao;

    private Usuarios usuarios;
    private Long usuarios__resolvedKey;

    private Sitios sitios;
    private Long sitios__resolvedKey;


    public Valoraciones() {
    }

    public Valoraciones(Long id) {
        this.id = id;
    }

    public Valoraciones(Long id, String objectId, String updateAt, String valoracion, long idUsuarioV, long idSitioV) {
        this.id = id;
        this.objectId = objectId;
        this.updateAt = updateAt;
        this.valoracion = valoracion;
        this.idUsuarioV = idUsuarioV;
        this.idSitioV = idSitioV;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getValoracionesDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public long getIdUsuarioV() {
        return idUsuarioV;
    }

    public void setIdUsuarioV(long idUsuarioV) {
        this.idUsuarioV = idUsuarioV;
    }

    public long getIdSitioV() {
        return idSitioV;
    }

    public void setIdSitioV(long idSitioV) {
        this.idSitioV = idSitioV;
    }

    /** To-one relationship, resolved on first access. */
    public Usuarios getUsuarios() {
        long __key = this.idUsuarioV;
        if (usuarios__resolvedKey == null || !usuarios__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UsuariosDao targetDao = daoSession.getUsuariosDao();
            Usuarios usuariosNew = targetDao.load(__key);
            synchronized (this) {
                usuarios = usuariosNew;
            	usuarios__resolvedKey = __key;
            }
        }
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        if (usuarios == null) {
            throw new DaoException("To-one property 'idUsuarioV' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.usuarios = usuarios;
            idUsuarioV = usuarios.getId();
            usuarios__resolvedKey = idUsuarioV;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Sitios getSitios() {
        long __key = this.idSitioV;
        if (sitios__resolvedKey == null || !sitios__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SitiosDao targetDao = daoSession.getSitiosDao();
            Sitios sitiosNew = targetDao.load(__key);
            synchronized (this) {
                sitios = sitiosNew;
            	sitios__resolvedKey = __key;
            }
        }
        return sitios;
    }

    public void setSitios(Sitios sitios) {
        if (sitios == null) {
            throw new DaoException("To-one property 'idSitioV' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.sitios = sitios;
            idSitioV = sitios.getId();
            sitios__resolvedKey = idSitioV;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
