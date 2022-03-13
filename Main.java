package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static ArrayList<Role> allRoles = new ArrayList<Role>(){};
    static ArrayList<User> allUsers = new ArrayList<User>(){};

    public static void main(String[] args) throws IOException {

        System.out.println("Enter root role name: ");
        String rootRoleName = br.readLine();
        Role level0 = new Role(rootRoleName, null);

        allRoles.add(level0);
        displayOptions();


    }




    public static void displayOptions() throws IOException {

        while (true){

            System.out.println("Operations:");
            System.out.println("    1. Add Sub Role. ");
            System.out.println("    2. Display Roles. ");             
            System.out.println("    3. Delete Roles. ");
            System.out.println("    4. Add user. ");
            System.out.println("    5. Display user ");
            System.out.println("    6. Display user and Sub-users "); 
            System.out.println("    7. Delete user ");                
            System.out.println("    8. Number Of Users from top ");
            System.out.println("    9. Height of role hierarchy ");   
            System.out.println("   10. Common boss of users\n ");     
            System.out.println("(misc)11. Display child of: ");
            System.out.println("    12. Exit ");

            int userInput = Integer.parseInt(br.readLine());

            switch (userInput){
                case 1: addSubRoles();
                        break;
                case 2: displayRoles();
                        break;
                case 3: deleteRoles();
                        break;
                case 4: addUser();
                        break;
                case 5: displayUser();
                        break;
                case 6: displayUserAndSubUser();
                        break;
                case 7: deleteUser();
                        break;
                case 8: NumberOfUsersFromTop();
                        break;
                case 9: getHeightOfRoleHierarchy();
                        break;
                case 10:break;
                case 11: displayChildRoles();
                        break;
                case 12: return;
                default: System.out.println("Invalid Option. Please select from available list");
                        break;
            }

        }
    }

    //Question 1
    public static void addSubRoles() throws IOException {

        try {

            System.out.println("Operation to be performed: 1");
            String subRoleName;
            String reportingToRoleName;

            System.out.print("Enter sub role name:");
            subRoleName = br.readLine();

            System.out.print("Enter reporting to role name:");
            reportingToRoleName = br.readLine();

            Role reportingToRole = (Role) allRoles.stream().filter(it -> it.getRoleName().equals(reportingToRoleName)).toArray()[0];
            Role subRole = new Role(subRoleName, reportingToRole);
            subRole.updateParent();
            allRoles.add(subRole);
        }
        catch (Exception e){
            System.out.println(" This is a base implementation. Looks like one or more entered values does not exists. Please" +
                    "enter available values only");
        }

    }



    //Question 2
    public static void displayRoles(){

        try {
            printChildRoles(allRoles.get(0));
        }catch (Exception e){
            System.out.println(" This is a base implementation. Looks like one or more entered values does not exists. Please" +
                    "enter available values only");
        }


    }

    public static void printChildRoles(Role parent){
        if(parent.getChildRoles().isEmpty())
            return;

        ArrayList<Role> childRoles = parent.getChildRoles();
        for (int i=0; i<childRoles.size();i++){
            System.out.println(childRoles.get(i).getRoleName());
        }

        for (int i=0; i<childRoles.size();i++){
            printChildRoles(childRoles.get(i));
        }
    }


    public static void displayChildRoles() throws IOException {

        try {
            String roleName = br.readLine();
            Role role = (Role) allRoles.stream().filter(it -> it.getRoleName().equals(roleName)).toArray()[0];
            ArrayList<Role> childRoles = role.getChildRoles();

            System.out.println("Child Roles: ");
            for (Role ch : childRoles) {
                System.out.println(ch.getRoleName());
            }
            System.out.println("-----------------");
        }catch (Exception e){
            System.out.println(" This is a base implementation. Looks like one or more entered values does not exists. Please" +
                    "enter available values only");
        }
    }



    //Question 3
    public static void deleteRoles() throws IOException {

        try {
            System.out.println("Operation to be performed: 3");

            System.out.println("Enter the role to be deleted: ");
            String roleToDelete_name = br.readLine();

            System.out.println("Enter role to be transferred: ");
            String newParentRole_name = br.readLine();


            if (roleToDelete_name == allRoles.get(0).getRoleName())
                System.out.println("Root role deletion not supported at the moment");


            Role roleToDelete = (Role) allRoles.stream().filter(it -> it.getRoleName().equals(roleToDelete_name)).toArray()[0];
            Role newParentRole = (Role) allRoles.stream().filter(it -> it.getRoleName().equals(newParentRole_name)).toArray()[0];

            ArrayList<Role> affectedChildRoles = roleToDelete.getChildRoles();
            for (Role affectedChildRole : affectedChildRoles) {

                for (int i = 0; i < allRoles.size(); i++) {
                    if (allRoles.get(i) == affectedChildRole) {
                        allRoles.get(i).setParent(newParentRole);
                        newParentRole.setChildRoles(affectedChildRole);

                    }

                }
            }

            Role parentOfDeletedRole = roleToDelete.getParent();
            parentOfDeletedRole.deleteChildRole(roleToDelete);

            allRoles.remove(roleToDelete);
        }catch (Exception e){
            System.out.println(" This is a base implementation. Looks like one or more entered values does not exists. Please" +
                    "enter available values only");
        }
    }



    //Question 4
    public static void addUser() throws IOException {

        try {


            String userName;
            String roleName;

            System.out.println("Operation to be performed: 4");

            System.out.println("Enter User Name:");
            userName = br.readLine();

            System.out.println("Enter Role:");
            roleName = br.readLine();
            Role assignedRole = (Role) allRoles.stream().filter(it -> it.getRoleName().equals(roleName)).toArray()[0];

            User newUser = new User(userName, assignedRole);
            allUsers.add(newUser);
        }catch (Exception e){
            System.out.println(" This is a base implementation. Looks like one or more entered values does not exists. Please" +
                    "enter available values only");
        }
    }



    //Question 5
    public static void displayUser(){

       for (User user : allUsers){
           System.out.println(user.getUserName()+" - "+user.getUserRole().getRoleName());
       }

    }



    //Question 6
    public static void displayUserAndSubUser(){

        System.out.println("Operation to be performed: 6");


    }



    //Question 7
    public static void deleteUser(){

    }



    //Question 8
    public static void NumberOfUsersFromTop() throws IOException {

        try {
            String userName;
            System.out.println("Enter user name: ");
            userName = br.readLine();

            User user = (User) allUsers.stream().filter(it -> it.getUserName().equals(userName)).toArray()[0];
            System.out.println("Number Of Users from Top: " + getTopLevelUsers(user).size());
        }catch (Exception e){
            System.out.println(" This is a base implementation. Looks like one or more entered values does not exists. Please" +
                    "enter available values only");
        }
    }

    public static ArrayList<User> getTopLevelUsers(User user){

        try {
            Role userRole = user.getUserRole();
            Role parentRole = userRole.getParent();
            ArrayList<User> topLevelUsers = allUsers.stream().filter(it -> it.getUserRole() == parentRole).collect(Collectors.toCollection(ArrayList::new));
            ArrayList<User> tempUserList = new ArrayList<User>() {
            };
            if (topLevelUsers.get(0).getUserRole().getParent() == null)
                return topLevelUsers;
            else
                for (User singleUser : topLevelUsers) {
                    for (User eachUser : getTopLevelUsers(singleUser)) {
                        if (eachUser != null) {

                            tempUserList.add(eachUser);
                        }

                    }
                }
            tempUserList = tempUserList.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
            topLevelUsers.addAll(tempUserList);
            return topLevelUsers;

        }catch (Exception e){
            System.out.println(" This is a base implementation. Looks like one or more entered values does not exists. Please" +
                    "enter available values only");
        }
        return null;
    }




    //Question 9
    public static void getHeightOfRoleHierarchy(){

    }


}
