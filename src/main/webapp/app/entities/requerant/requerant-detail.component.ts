import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequerant } from 'app/shared/model/requerant.model';

@Component({
  selector: 'jhi-requerant-detail',
  templateUrl: './requerant-detail.component.html',
})
export class RequerantDetailComponent implements OnInit {
  requerant: IRequerant | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requerant }) => (this.requerant = requerant));
  }

  previousState(): void {
    window.history.back();
  }
}
