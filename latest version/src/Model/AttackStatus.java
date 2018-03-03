package Model;

import java.io.Serializable;

public enum AttackStatus implements Serializable {
    FAILURE, PARTIAL_DAMAGE_OF_SHIP, DESTROYED_SHIP, DESTROYED_ALL_SHIPS, DESTROYED_BOMB;
}


