package generate;

import generate.BrokerRole;

public interface BrokerRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BrokerRole record);

    int insertSelective(BrokerRole record);

    BrokerRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BrokerRole record);

    int updateByPrimaryKey(BrokerRole record);
}