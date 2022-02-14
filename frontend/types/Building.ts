export type Building = {
  id: String,
  features: Map<String, Material>
}

type Material = {
  name: String,
  icon: String,
}
