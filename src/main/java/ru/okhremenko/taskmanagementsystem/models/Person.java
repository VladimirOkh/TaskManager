    package ru.okhremenko.taskmanagementsystem.models;
    import javax.persistence.*;
    import java.util.List;

    @Entity
    @Table(name = "Person")
    public class Person {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "role")
        private String role;

        @OneToMany(mappedBy = "assignedTo")
        private List<Task> tasks;

        public List<Task> getTasks() {
            return tasks;
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
        }

        public Person() {
        }

        public Person(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", username=" + username +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
