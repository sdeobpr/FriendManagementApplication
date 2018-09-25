package com.friendshipApp.model;

import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "FS_PROFILE_MAPPING_DTL")
@Component("friendGraphMappingDetails")
@XmlRootElement
@SequenceGenerator(name = "seq_friend_mapping", sequenceName = "seq_friend_mapping", initialValue = 1, allocationSize = 1)
public class FriendGraphMappingDetails 
{
	@Id
    @GeneratedValue(generator = "seq_friend_mapping" )
    @Column(name = "Mapping_Id", length = 256, updatable = false,
            nullable = false)
    private Long mappingId;
	
	@Lob
	@Column(name="FriendShip_Mapping_Graph" , columnDefinition = "text" )	
	private LinkedList<Integer> friendshipgraph[];
	
	@Lob
	@Column(name="Subscribe_Mapping_Graph")
	private LinkedList<Integer> subscribegraph[];
	
	@Lob
	@Column(name="Blocking_Mapping_Graph")
	private LinkedList<Integer> blockgraph[];

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public LinkedList<Integer>[] getSubscribegraph() {
		return subscribegraph;
	}

	public void setSubscribegraph(LinkedList<Integer>[] subscribegraph) {
		this.subscribegraph = subscribegraph;
	}

	public LinkedList<Integer>[] getBlockgraph() {
		return blockgraph;
	}

	public void setBlockgraph(LinkedList<Integer>[] blockgraph) {
		this.blockgraph = blockgraph;
	}

	public LinkedList<Integer>[] getFriendshipgraph() {
		return friendshipgraph;
	}

	public void setFriendshipgraph(LinkedList<Integer>[] friendshipgraph) {
		this.friendshipgraph = friendshipgraph;
	}
	
	
}
