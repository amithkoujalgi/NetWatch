package io.github.amithkoujalgi.netwatch.models.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GraphNode {

  private GraphNodeData data;
  private GraphNodePosition position;
}
