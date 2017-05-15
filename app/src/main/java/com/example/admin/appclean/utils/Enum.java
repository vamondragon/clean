package com.example.admin.appclean.utils;

public class Enum {

    public enum ConfiguracionApp {
        TERMINO_TUTORIAL("pref_termino_tutorial"),
        ACEPTO_PERMISOS("pref_permisos_app");

        private String value;

        ConfiguracionApp(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

}
