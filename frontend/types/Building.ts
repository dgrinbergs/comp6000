export type Building = {
  buildingId: String,
  floor: Floor,
  perimeter: Perimeter<Wall>
  window: Window,
  roof: Roof,
  door: Door,
  cornerPerimeter: Perimeter<Corner>,
  doorNum: DoorNum,
  bed: Bed,
  decor: Decor,
}

interface Feature {
  displayName: string,
  name: string,
}

interface Perimeter<T> {
  north: T,
  east: T,
  south: T,
  west: T,
}

type Floor = Feature;
type Wall = Feature;
type Window = Feature;
type Roof = Feature;
type Door = Feature;
type Corner = Feature;
type DoorNum = Feature
type Bed = Feature;
type Decor = Feature;
