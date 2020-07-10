import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEthnie } from 'app/shared/model/ethnie.model';

@Component({
  selector: 'jhi-ethnie-detail',
  templateUrl: './ethnie-detail.component.html',
})
export class EthnieDetailComponent implements OnInit {
  ethnie: IEthnie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ethnie }) => (this.ethnie = ethnie));
  }

  previousState(): void {
    window.history.back();
  }
}
