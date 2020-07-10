import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISaisine } from 'app/shared/model/saisine.model';

@Component({
  selector: 'jhi-saisine-detail',
  templateUrl: './saisine-detail.component.html',
})
export class SaisineDetailComponent implements OnInit {
  saisine: ISaisine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ saisine }) => (this.saisine = saisine));
  }

  previousState(): void {
    window.history.back();
  }
}
