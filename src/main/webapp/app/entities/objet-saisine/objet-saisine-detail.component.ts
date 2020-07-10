import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObjetSaisine } from 'app/shared/model/objet-saisine.model';

@Component({
  selector: 'jhi-objet-saisine-detail',
  templateUrl: './objet-saisine-detail.component.html',
})
export class ObjetSaisineDetailComponent implements OnInit {
  objetSaisine: IObjetSaisine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objetSaisine }) => (this.objetSaisine = objetSaisine));
  }

  previousState(): void {
    window.history.back();
  }
}
