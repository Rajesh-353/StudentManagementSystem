package in.naveen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardResponse {

	private Integer totalEnq;
	private Integer enrollEnq;
	private Integer lostEnq;
}
