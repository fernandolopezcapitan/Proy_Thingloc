package com.dam.salesianostriana.di.thinglocv1.greendao;

import java.util.List;
import com.dam.salesianostriana.di.thinglocv1.greendao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SITIOS".
 */
public class Sitios {

    private Long id;
    private String objectId;
    private String updateAt;
    private String nombre;
    private String categoria;
    private String direccion;
    private String tlf;
    private String descripcion;
    private String fotoUrl;
    private String latitud;
    private String longitud;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient SitiosDao myDao;

    private List<Comentarios> comentariosDeUnSitio;

    public Sitios() {
    }

    public Sitios(Long id) {
        this.id = id;
    }

    public Sitios(Long id, String objectId, String updateAt, String nombre, String categoria, String direccion, String tlf, String descripcion, String fotoUrl, String latitud, String longitud) {
        this.id = id;
        this.objectId = objectId;
        this.updateAt = updateAt;
        this.nombre = nombre;
        this.categoria = categoria;
        this.direccion = direccion;
        this.tlf = tlf;
        this.descripcion = descripcion;
        this.fotoUrl = fotoUrl;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSitiosDao() : null;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Comentarios> getComentariosDeUnSitio() {
        if (comentariosDeUnSitio == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ComentariosDao targetDao = daoSession.getComentariosDao();
            List<Comentarios> comentariosDeUnSitioNew = targetDao._querySitios_ComentariosDeUnSitio(id);
            synchronized (this) {
                if(comentariosDeUnSitio == null) {
                    comentariosDeUnSitio = comentariosDeUnSitioNew;
                }
            }
        }
        return comentariosDeUnSitio;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetComentariosDeUnSitio() {
        comentariosDeUnSitio = null;
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
