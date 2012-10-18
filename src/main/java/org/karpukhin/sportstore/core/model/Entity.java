package org.karpukhin.sportstore.core.model;

/**
 * This class represents abstract entity of domain model
 * @author Pavel Karpukhin
 * @since 18.10.12
 */
public class Entity {

    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	@Override
	public int hashCode() {
		return 31 + id;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Entity that = (Entity) other;
		return id == that.id;
	}
}
