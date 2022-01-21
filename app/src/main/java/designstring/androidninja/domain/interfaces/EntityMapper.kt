package designstring.androidninja.domain.interfaces

/**
 * This interface is not useful much if only one type of conversion is needed,
 * as in this case only from network entity to domain entity for showing to the user.
 * But would have been useful if there was databases and POST requests.
 */
interface EntityMapper<Entity, DomainEntity> {

    fun toDomainEntity(otherEntity: Entity): DomainEntity

}