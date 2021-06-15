package me.border.userbot.storage;

import me.border.utilities.database.sql.IMySQLDB;

public class MySQLDB extends IMySQLDB {

    public MySQLDB(String host, String database, String username, String password, int port) {
        super(host, database, username, password, port);
    }

    public void createIdsTable(){
        execute("CREATE TABLE IF NOT EXISTS ids(id VARCHAR(32) NOT NULL, PRIMARY KEY(id));");
    }
}
