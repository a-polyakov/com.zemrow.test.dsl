/*
 * This file is generated by jOOQ.
*/
package com.zemrow.test.dsl.jooq.autogen.auth.dao.entity;


import java.io.Serializable;
import java.util.UUID;


/**
 * Пользователь
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class AuthUserEntity implements Serializable {

    private static final long serialVersionUID = -638655169;

    private UUID id;
    private String label;
    private Long createTime;
    private UUID createdBy;
    private Long updateTime;
    private UUID updatedBy;
    private Long deleteTime;
    private UUID deletedBy;

    public AuthUserEntity() {
    }

    public AuthUserEntity(AuthUserEntity value) {
        this.id = value.id;
        this.label = value.label;
        this.createTime = value.createTime;
        this.createdBy = value.createdBy;
        this.updateTime = value.updateTime;
        this.updatedBy = value.updatedBy;
        this.deleteTime = value.deleteTime;
        this.deletedBy = value.deletedBy;
    }

    public AuthUserEntity(
            UUID id,
            String label,
            Long createTime,
            UUID createdBy,
            Long updateTime,
            UUID updatedBy,
            Long deleteTime,
            UUID deletedBy
    ) {
        this.id = id;
        this.label = label;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.deleteTime = deleteTime;
        this.deletedBy = deletedBy;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public UUID getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public UUID getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getDeleteTime() {
        return this.deleteTime;
    }

    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public UUID getDeletedBy() {
        return this.deletedBy;
    }

    public void setDeletedBy(UUID deletedBy) {
        this.deletedBy = deletedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AuthUserEntity other = (AuthUserEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (createdBy == null) {
            if (other.createdBy != null)
                return false;
        } else if (!createdBy.equals(other.createdBy))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        if (updatedBy == null) {
            if (other.updatedBy != null)
                return false;
        } else if (!updatedBy.equals(other.updatedBy))
            return false;
        if (deleteTime == null) {
            if (other.deleteTime != null)
                return false;
        } else if (!deleteTime.equals(other.deleteTime))
            return false;
        if (deletedBy == null) {
            if (other.deletedBy != null)
                return false;
        } else if (!deletedBy.equals(other.deletedBy))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
        result = prime * result + ((deleteTime == null) ? 0 : deleteTime.hashCode());
        result = prime * result + ((deletedBy == null) ? 0 : deletedBy.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AuthUserEntity (");

        sb.append(id);
        sb.append(", ").append(label);
        sb.append(", ").append(createTime);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updateTime);
        sb.append(", ").append(updatedBy);
        sb.append(", ").append(deleteTime);
        sb.append(", ").append(deletedBy);

        sb.append(")");
        return sb.toString();
    }
}