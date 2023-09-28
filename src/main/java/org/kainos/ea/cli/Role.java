package org.kainos.ea.cli;
    public enum Role{
        Admin(1),
        Employee(2);

        private final int roleID;

        Role(int roleID) {
            this.roleID = roleID;
        }

        public int getRole() {
            return roleID;
        }

        public static Role fromID(int id) {
            switch (id) {
                case 1:
                    return Admin;
                case 2:
                    return Employee;
            }

            return null;
        }
}
