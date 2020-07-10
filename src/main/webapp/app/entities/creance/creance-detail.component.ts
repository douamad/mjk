import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICreance } from 'app/shared/model/creance.model';

@Component({
  selector: 'jhi-creance-detail',
  templateUrl: './creance-detail.component.html',
})
export class CreanceDetailComponent implements OnInit {
  creance: ICreance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ creance }) => (this.creance = creance));
  }

  previousState(): void {
    window.history.back();
  }
}
