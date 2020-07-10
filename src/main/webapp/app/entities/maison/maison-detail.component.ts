import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMaison } from 'app/shared/model/maison.model';

@Component({
  selector: 'jhi-maison-detail',
  templateUrl: './maison-detail.component.html',
})
export class MaisonDetailComponent implements OnInit {
  maison: IMaison | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maison }) => (this.maison = maison));
  }

  previousState(): void {
    window.history.back();
  }
}
