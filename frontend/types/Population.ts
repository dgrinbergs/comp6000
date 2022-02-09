import {Building} from "~/types/Building";

export type Population = {
  populationId: string,
  buildings: Building[],
  selected: string[],
}
