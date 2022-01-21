package designstring.androidninja.domain.mappers

import designstring.androidninja.data.network.models.NetworkEntityPeople
import designstring.androidninja.domain.interfaces.EntityMapper
import designstring.androidninja.domain.models.DomainEntity
import javax.inject.Inject

class NetworkEntityMapper
@Inject
constructor()
    : EntityMapper<NetworkEntityPeople, DomainEntity> {
    override fun toDomainEntity(otherEntity: NetworkEntityPeople): DomainEntity {
        return DomainEntity(
            otherEntity.first_name,
            otherEntity.email,
            otherEntity.avatar,
        )
    }
}