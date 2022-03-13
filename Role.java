package com.company;
import java.util.ArrayList;

public class Role {

    private String roleName;
    private Role parent;
    ArrayList<Role> childRoles;

    Role(String roleName, Role parent){
        this.parent = parent;
        this.roleName = roleName;
        this.childRoles = new ArrayList<Role>(){};
    }

    void updateParent(){
        parent.setChildRoles(this);
    }

    void setChildRoles(Role child){
        this.childRoles.add(child);
    }

    public void setParent(Role parent){
        this.parent = parent;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Role getParent() {
        return this.parent;
    }

    public ArrayList<Role> getChildRoles() {
        return childRoles;
    }

    public void deleteChildRole(Role childRoleToDelete){
        childRoles.removeIf( it -> it.getRoleName().equals(childRoleToDelete.getRoleName()));
    }
}
