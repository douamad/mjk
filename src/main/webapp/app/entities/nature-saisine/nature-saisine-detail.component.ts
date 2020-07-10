import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INatureSaisine } from 'app/shared/model/nature-saisine.model';

@Component({
  selector: 'jhi-nature-saisine-detail',
  templateUrl: './nature-saisine-detail.component.html',
})
export class NatureSaisineDetailComponent implements OnInit {
  natureSaisine: INatureSaisine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureSaisine }) => (this.natureSaisine = natureSaisine));
  }

  previousState(): void {
    window.history.back();
  }
}
