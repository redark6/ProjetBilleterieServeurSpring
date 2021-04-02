package fr.montreuil.iut.csid.group.alpha.projetBilleterieSpring.dto;


import liquibase.pro.packaged.A;

public class AuthorityDto{

        private String id;

        private String authority;

        public AuthorityDto(String id, String authority) {
            this.id = id;
            this.authority = authority;
        }
        public AuthorityDto(){
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

}
