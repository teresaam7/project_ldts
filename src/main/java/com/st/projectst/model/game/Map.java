package com.st.projectst.model.game;

import com.st.projectst.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map {
    private int width;
    private int height;
    private int currentLevel;

    private Mari mari;
    private List<GhostEnemy> gEnemies;
    private List<BatEnemy> bEnemies;
    private List<Wall> walls;
    private List<Platform> platforms;
    private Key key;
    private Door door;
    private List<Trap> traps;
    private List<Potion> potions;

    public Map (int width, int height, int currentLevel) {
        this.width = width;
        this.height = height;
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Mari getMari() {
        return mari;
    }
    public void setMari(Mari mari) {
        this.mari = mari;
    }

    public List<GhostEnemy> getGhostEnemies() {
        return gEnemies;
    }
    public void setGhostEnemies(List<GhostEnemy> gEnemies) {
        this.gEnemies = gEnemies;
    }

    public List<BatEnemy> getBatEnemies() {
        return bEnemies;
    }
    public void setBatEnemies(List<BatEnemy> bEnemies) {
        this.bEnemies = bEnemies;
    }

    public List<Wall> getWalls() {
        return walls;
    }
    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }
    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public Key getKey() {
        return key;
    }
    public void setKey(Key key) {
        this.key = key;
    }

    public List<Potion> getPotions() {
        return potions;
    }
    public void setPotions(List<Potion> potions) {
        this.potions = potions;
    }

    public List<Trap> getTraps() {
        return traps;
    }
    public void setTraps(List<Trap> traps) {
        this.traps = traps;
    }

    public Door getDoor() {
        return door;
    }
    public void setDoor(Door door) {
        this.door = door;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


    public boolean isAtDoor(Position position) {
        Position mariPosition = new Position(position);
        mariPosition.setX(mariPosition.getX()+11);
        return door.getPosition().equals(mariPosition);
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        for (Platform platform : platforms)
                if (platform.getPosition().equals(position))
                    return false;
        return true;
    }

    public boolean isEnemy(Position position) {
        Position position2 = new Position(position); position2.setX(position.getX()+13);

        for (GhostEnemy enemy : getGhostEnemies()) {
            Position enemyPosition = new Position(enemy.getPosition());
            enemyPosition.setX(enemyPosition.getX()+4);
            if (enemyPosition.equals(position) || enemyPosition.equals(position2))
                return true;
        }
        for (BatEnemy enemy : getBatEnemies()) {
            Position enemyPosition = new Position(enemy.getPosition());
            enemyPosition.setX(enemyPosition.getX()+7);
            if (enemyPosition.equals(position) || enemyPosition.equals(position2))
                return true;
        }
        return false;
    }

    public boolean mariIsGrounded() {
        Position floorPosition = new Position(mari.getPosition());
        floorPosition.setY(floorPosition.getY()+14);

        List<Position> floorPositions = new ArrayList<>();
        for (int i = 3; i < 9; i++) {
            Position newFloorPosition = new Position(floorPosition);
            newFloorPosition.setX(newFloorPosition.getX()+i);
            floorPositions.add(newFloorPosition);
        }

        for (Position pos: floorPositions) {
            for (Wall wall : walls) {
                if (Objects.equals(wall.getPosition(), pos))
                    return true;
            }
            for (Platform platform : platforms){
                Position newPos = new Position(pos);
                newPos.setY(newPos.getY()-1);
                if (platform.getPosition().equals(pos) || platform.getPosition().equals(newPos))
                    return true;
            }
        }
        return false;
    }

    public boolean isTrap() {
        Position trapPosition = new Position(mari.getPosition());
        trapPosition.setY(trapPosition.getY()+13);

        List<Position> trapPositions = new ArrayList<>();
        for (int i = 3; i < 9; i++) {
            Position newFloorPosition = new Position(trapPosition);
            newFloorPosition.setX(newFloorPosition.getX()+i);
            trapPositions.add(newFloorPosition);
        }

        for (Position pos: trapPositions) {
            for (Trap trap : traps){
                if (trap.getPosition().equals(pos)) {
                    trap.notifyObservers();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isKey(Position position) {
        if (key != null) {
            Position pos = new Position(position);
            pos.setY(position.getY()+7);
            return key.getPosition().equals(pos);
        }
        return false;
    }

    public boolean isAtPlatform(Position currentMariPosition) {
        Position floorPosition = new Position(currentMariPosition);
        floorPosition.setY(floorPosition.getY()+13);

        List<Position> floorPositions = new ArrayList<>();
        for (int i = 3; i < 9; i++) {
            Position newFloorPosition = new Position(floorPosition);
            newFloorPosition.setX(newFloorPosition.getX()+i);
            floorPositions.add(newFloorPosition);
        }

        for (Position pos: floorPositions) {
            for (Platform platform : platforms){
                if (platform.getPosition().equals(pos)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean touchPotion(Position position) {
        for (Potion potion : potions) {
            if (potion.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public void removeKey() {
        this.key = null;
    }
}