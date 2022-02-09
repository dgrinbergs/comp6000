export type Building = {
  uuid: String,
  floor: Floor,
  perimeter: Perimeter
}

type Floor = {
  displayName: string,
  name: string,
}

type Wall = Floor;

type Perimeter = {
  north: Wall,
  east: Wall,
  south: Wall,
  west: Wall,
}
