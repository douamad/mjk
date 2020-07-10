import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObjetAssistance } from 'app/shared/model/objet-assistance.model';

@Component({
  selector: 'jhi-objet-assistance-detail',
  templateUrl: './objet-assistance-detail.component.html',
})
export class ObjetAssistanceDetailComponent implements OnInit {
  objetAssistance: IObjetAssistance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objetAssistance }) => (this.objetAssistance = objetAssistance));
  }

  previousState(): void {
    window.history.back();
  }
}
