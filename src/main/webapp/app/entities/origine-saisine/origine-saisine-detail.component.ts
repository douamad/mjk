import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrigineSaisine } from 'app/shared/model/origine-saisine.model';

@Component({
  selector: 'jhi-origine-saisine-detail',
  templateUrl: './origine-saisine-detail.component.html',
})
export class OrigineSaisineDetailComponent implements OnInit {
  origineSaisine: IOrigineSaisine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ origineSaisine }) => (this.origineSaisine = origineSaisine));
  }

  previousState(): void {
    window.history.back();
  }
}
